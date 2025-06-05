package hva;

import java.io.*;
import hva.exceptions.*;
import hva.Hotel;

/**
 * Class that represents the hotel application.
 */
public class HotelManager {

    /** This is the current hotel. */
    private Hotel _hotel = new Hotel();
    private String _filename;
    private boolean _UnsavedChanges;   
    
    /**
     * Saves the serialized application's state into the file associated to the
     * current network.
     *
     * @throws FileNotFoundException           if for some reason the file cannot be
     *                                         created or opened.
     * @throws MissingFileAssociationException if the current network does not have
     *                                         a file.
     * @throws IOException                     if there is some error while
     *                                         serializing the state of the network
     *                                         to disk.
     */

    public void save() throws FileNotFoundException,
            MissingFileAssociationException, IOException {
        if (_filename == null) {
            throw new MissingFileAssociationException();
        } else {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(_filename));
            out.writeObject(_hotel);
            setUnsavedChanges(false);
            out.close();
        }
    }

    /**
     * Saves the serialized application's state into the file associated to the
     * current network.
     *
     * @throws FileNotFoundException           if for some reason the file cannot be
     *                                         created or opened.
     * @throws MissingFileAssociationException if the current network does not have
     *                                         a file.
     * @throws IOException                     if there is some error while
     *                                         serializing the state of the network
     *                                         to disk.
     */
    public void saveAs(String filename) throws FileNotFoundException,
            MissingFileAssociationException, IOException {
        if (filename == null || filename.isEmpty()) {
            throw new MissingFileAssociationException();
        }
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(filename))) {
            out.writeObject(_hotel);
            setUnsavedChanges(false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param filename name of the file containing the serialized application's
     *                 state
     *                 to load.
     * @throws UnavailableFileException if the specified file does not exist or
     *                                  there is
     *                                  an error while processing this file.
     */

    public void load(String filename) throws UnavailableFileException {
        _filename = filename;

        if (filename == null || filename.equals("")
                | filename.isEmpty()) {
            throw new UnavailableFileException(filename);
        }
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(filename))) {
            _hotel = (Hotel) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new UnavailableFileException(filename);
        }
    }

    /**
     * Read text input file.
     *
     * @param filename name of the text input file
     * @throws ImportFileException
     */

    public void importFile(String filename) throws ImportFileException {
        _hotel.importFile(filename);
        setUnsavedChanges(true);
    }

    public Hotel getHotel() {
        return _hotel;
    }

    public void setUnsavedChanges(boolean unsavedChanges) {
        _UnsavedChanges = unsavedChanges;
    }

    public boolean getUnsavedChanges() {
        return _UnsavedChanges;
    }

    public String getFilename() {
        return _filename;
    }

    public void resetAll() {
        _hotel = new Hotel();
        _filename = null;
        _UnsavedChanges = true;
    }


    public int redirectToSeason() {
        setUnsavedChanges(true);
        return _hotel.Advance_Season();
    }
}

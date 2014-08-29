package org.lonewanderer.ellie;

/**
 * Created by Davide on 28/08/2014.
 */
public final class LocalDataParser {

    LocalDataParser() {
    }

    /**
     * Parse the data read from QR Code to identify the local
     * @param raw the data read from QR Code
     * @return a String array of local's information if the QR code is valid, null otherwise
     */
    public static String[] parseQrCodeData(String raw)
    {
        String[] localData = raw.split(";");

        if(localData != null)
            if(localData.length == 3)
                return localData;

        return null;
    }
}

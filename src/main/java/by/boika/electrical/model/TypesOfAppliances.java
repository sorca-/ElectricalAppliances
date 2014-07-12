package by.boika.electrical.model;

import org.apache.log4j.Logger;

public enum TypesOfAppliances {
    HAIRDRYER,
    KETTLE,
    MEDIA_CENTER,
    PHOTO_CAMERA;

    public static boolean hasType (String srcType) {
        Logger LOGGER = Logger.getLogger(TypesOfAppliances.class);
        srcType = srcType.toUpperCase();
        for (TypesOfAppliances typesOfAppliances : TypesOfAppliances.values()) {
            if (typesOfAppliances.toString().equals(srcType)) {
                return true;
            }
        }
        return false;
    }
}

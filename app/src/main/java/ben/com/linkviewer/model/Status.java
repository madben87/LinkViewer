package ben.com.linkviewer.model;

import ben.com.linkviewer.R;

public enum Status {

    ACTIVE(R.color.linkActive), ERROR(R.color.linkError), UNKNOWN(R.color.linkUnknown);

    private int value;

    Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}


package com.example.contacts.gravatar;

public class Gravatar {
    public static final int MIN_IMAGE_SIZE_PIXEL = 1;
    public static final int MAX_IMAGE_SIZE_PIXEL = 2048;
    private static Gravatar singleton;
    final boolean ssl;
    final boolean extension;

    private Gravatar(final boolean ssl, final boolean extension) {
        this.ssl = ssl;
        this.extension = extension;
    }

    public static Gravatar init() {
        if (Gravatar.singleton == null) {
            Gravatar.singleton = new Builder().build();
        }
        return Gravatar.singleton;
    }

    public RequestBuilder with(final String email) {
        return new RequestBuilder(this, email);
    }

    static {
        Gravatar.singleton = null;
    }

    public static class Builder
    {
        private boolean ssl;
        private boolean extension;

        public Builder() {
            this.ssl = false;
            this.extension = false;
        }

        public Builder ssl() {
            this.ssl = true;
            return this;
        }

        public Builder fileExtension() {
            this.extension = true;
            return this;
        }

        public Gravatar build() {
            return new Gravatar(this.ssl, this.extension);
        }
    }

    public static class DefaultImage
    {
        public static final int MYSTERY_MAN = 0;
        public static final int IDENTICON = 1;
        public static final int MONSTER = 2;
        public static final int WAVATAR = 3;
        public static final int RETRO = 4;
        public static final int BLANK = 5;
    }

    public static class Rating
    {
        public static final int g = 0;
        public static final int pg = 1;
        public static final int r = 2;
        public static final int x = 3;
    }
}
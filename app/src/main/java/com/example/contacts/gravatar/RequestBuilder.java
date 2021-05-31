package com.example.contacts.gravatar;

public class RequestBuilder {
    private static final String GRAVATAR_ENDPOINT = "http://www.gravatar.com/avatar/";
    private static final String GRAVATAR_SECURE_ENDPOINT = "https://secure.gravatar.com/avatar/";
    private final StringBuilder builder;

    RequestBuilder(final Gravatar gravatar, final String email) {
        final String hash = Utils.convertEmailToHash(email);
        (this.builder = (gravatar.ssl ? new StringBuilder("https://secure.gravatar.com/avatar/".length() + hash.length() + 1).append("https://secure.gravatar.com/avatar/") : new StringBuilder("http://www.gravatar.com/avatar/".length() + hash.length() + 1).append("http://www.gravatar.com/avatar/"))).append(hash);
        if (gravatar.extension) {
            this.builder.append(".jpg");
        }
        this.builder.append("?");
    }

    public RequestBuilder size(final int sizeInPixels) {
        if (sizeInPixels >= 1 && sizeInPixels <= 2048) {
            this.builder.append("&size=").append(sizeInPixels);
            return this;
        }
        throw new IllegalArgumentException("Requested image size must be between 1 and 2048");
    }

    public RequestBuilder forceDefault() {
        this.builder.append("&f=y");
        return this;
    }

    public RequestBuilder force404() {
        this.builder.append("&d=404");
        return this;
    }

    public RequestBuilder defaultImage(final int gravatarDefaultImage) {
        switch (gravatarDefaultImage) {
            case 0: {
                this.builder.append("&d=mm");
                return this;
            }
            case 1: {
                this.builder.append("&d=identicon");
                return this;
            }
            case 2: {
                this.builder.append("&d=monsterid");
                return this;
            }
            case 3: {
                this.builder.append("&d=wavatar");
                return this;
            }
            case 4: {
                this.builder.append("&d=retro");
                return this;
            }
            case 5: {
                this.builder.append("&d=blank");
                return this;
            }
            default: {
                throw new IllegalArgumentException("The Gravatar default image must be one of the built-in default images MYSTERY_MAN, IDENTICON, MONSTER, WAVATAR, RETRO, BLANK or a custom URL image");
            }
        }
    }

    public RequestBuilder defaultImage(final String url) {
        this.builder.append("&d=").append(Utils.encode(url));
        return this;
    }

    public RequestBuilder rating(final int rating) {
        switch (rating) {
            case 0: {
                this.builder.append("&r=g");
                return this;
            }
            case 1: {
                this.builder.append("&r=pg");
                return this;
            }
            case 2: {
                this.builder.append("&r=r");
                return this;
            }
            case 3: {
                this.builder.append("&r=x");
                return this;
            }
            default: {
                throw new IllegalArgumentException("The Gravatar default image must be one of the built-in rating policyG, PG, R or X");
            }
        }
    }

    public String build() {
        final int size = this.builder.length() - 1;
        if (this.builder.charAt(size) == '?') {
            this.builder.deleteCharAt(size);
        }
        return this.builder.toString();
    }
}

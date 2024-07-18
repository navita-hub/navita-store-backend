package br.com.navita.store.model;

public enum RequestType {

    APP_LIST, DELETE_APP, SYNC_APP_LIST, APP_ID;

    @Override
    public String toString() {
        return this.name();
    }
}

package com.koror.app.api;

import java.io.IOException;

public interface IDataIO {

    void saveDataSerialization() throws IOException;

    void loadDataSerialization() throws IOException, ClassNotFoundException;

    void saveDataXml() throws IOException;

    void loadDataXml() throws IOException;

    void saveDataJson() throws IOException;

    void loadDataJson() throws IOException;
}

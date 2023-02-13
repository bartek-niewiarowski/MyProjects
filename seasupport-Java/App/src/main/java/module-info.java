module src {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    opens src.app to javafx.fxml;
    requires jakarta.persistence;
    exports src.app;
    exports src.logic;
    exports src.appActions;
    requires org.hibernate.orm.core;
    requires com.oracle.database.jdbc;
    requires java.naming;
    opens src.logic to org.hibernate.orm.core;
//    requires org.hibernate.commons.annotations;
    //requires transitive org.apiguardian.api;
    //requires transitive org.junit.platform.commons;
    //requires transitive org.opentest4j;
    //requires transitive org.junit.jupiter.api;
    //requires net.bytebuddy;
    //requires org.junit.jupiter.params;
    requires java.xml.crypto;
    requires java.desktop;
    requires org.tinylog.api;
}
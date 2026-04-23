module com.example.projetora2arvorebinaria {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projetora2arvorebinaria to javafx.fxml;
    exports com.example.projetora2arvorebinaria;
}
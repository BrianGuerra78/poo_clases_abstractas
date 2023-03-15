package org.bguerra.pooclasesabstractas.form;

import org.bguerra.pooclasesabstractas.form.elementos.*;
import org.bguerra.pooclasesabstractas.form.elementos.select.Opcion;
import org.bguerra.pooclasesabstractas.form.validador.*;

import java.util.Arrays;
import java.util.List;

public class EjemploForm {
    public static void main(String[] args) {

        InputForm username = new InputForm("username");
        username.addValidador(new RequeridoValidador());
        InputForm password = new InputForm("clave", "password");
        password.addValidador(new RequeridoValidador())
                .addValidador(new LargoValidador());
        InputForm email = new InputForm("email", "email");
        email.addValidador(new RequeridoValidador())
                .addValidador(new EmailValidador());
        InputForm edad = new InputForm("edad", "number");
        edad.addValidador(new NumeroValidador());
        TextareaForm experiencia = new TextareaForm("exp", 5, 9);

        SelectForm lenguaje = new SelectForm("lenguaje");
        lenguaje.addValidador(new NoNuloValidador());

        Opcion java = new Opcion("1", "Java");
        // Opcion typeScript = new Opcion("4", "TypeScript");
        lenguaje.addOpcion(java)
                .addOpcion(new Opcion("2", "Python"))
                .addOpcion(new Opcion("3", "JavaScript"))
                .addOpcion(new Opcion("4", "TypeScript").setSelected())
                //.addOpcion(typeScript)
                .addOpcion(new Opcion("5", "PHP"));

        ElementoForm saludar = new ElementoForm("saludo") {
            @Override
            public String dibujarHtml() {
                return "<input disabled name='" + this.nombre + "' value=\"" + this.valor + "\">";
            }
        };

        saludar.setValor("Hola que tal este campo esta desabilitado");
        username.setValor("");
        password.setValor("qaz22ed#");
        email.setValor("brian.gue@correo.com");
        edad.setValor("26");
        experiencia.setValor("... mas de 10 años de experiencia...");
        //java.setSelected(true);
        //typeScript.setSelected(true);

        List<ElementoForm> elementos = Arrays.asList(username, password, email, edad, experiencia, lenguaje, saludar);
        /*List<ElementoForm> elementos = new ArrayList<>();
        elementos.add(username);
        elementos.add(password);
        elementos.add(email);
        elementos.add(edad);
        elementos.add(experiencia);
        elementos.add(lenguaje);

        for (ElementoForm e : elementos) {
            System.out.println(e.dibujarHtml());
            System.out.println("<br>");
        }*/

        elementos.forEach(e -> {
            System.out.println(e.dibujarHtml());
            System.out.println("<br>");
        });

        elementos.forEach(e -> {
            if ((!e.esValido())) {
                e.getErrores().forEach(System.out::println);
                //e.getErrores().forEach(err -> System.out.println(e.getNombre() + ": " + err));
            }
        });
    }
}
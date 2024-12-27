package co.lunadev.adoptaweb.controllers.response;

import co.lunadev.adoptaweb.models.Departamento;
import co.lunadev.adoptaweb.models.Especie;

import java.util.List;

public class FilterAnimal {
    private List<Departamento> departamentos;
    private List<Especie> especies;

    public FilterAnimal(List<Departamento> departamentos, List<Especie> especies) {
        this.departamentos = departamentos;
        this.especies = especies;
    }
}

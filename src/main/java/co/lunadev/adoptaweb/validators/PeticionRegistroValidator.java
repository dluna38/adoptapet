package co.lunadev.adoptaweb.validators;

import co.lunadev.adoptaweb.controllers.dto_requests.NewPeticionRegistroRequest;
import co.lunadev.adoptaweb.repositories.MunicipioRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class PeticionRegistroValidator implements Validator {

    private MunicipioRepository municipioRepository;

    public PeticionRegistroValidator(MunicipioRepository municipioRepository) {
        this.municipioRepository = municipioRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return NewPeticionRegistroRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors e) {
        NewPeticionRegistroRequest model = (NewPeticionRegistroRequest) target;
        System.out.println(model.getUbicacionMunicipio());

    }
}

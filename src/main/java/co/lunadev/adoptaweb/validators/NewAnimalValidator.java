package co.lunadev.adoptaweb.validators;

import co.lunadev.adoptaweb.controllers.dto_requests.NewAnimalRequest;
import co.lunadev.adoptaweb.controllers.dto_requests.NewPeticionRegistroRequest;
import co.lunadev.adoptaweb.controllers.dto_requests.UpdateAnimalRequest;
import co.lunadev.adoptaweb.models.Animal;
import co.lunadev.adoptaweb.repositories.MunicipioRepository;
import co.lunadev.adoptaweb.repositories.RazaRepository;
import co.lunadev.adoptaweb.utils.UtilString;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service
public class NewAnimalValidator implements Validator {

    RazaRepository razaRepository;

    public NewAnimalValidator(RazaRepository razaRepository) {
        this.razaRepository = razaRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return NewAnimalRequest.class.equals(clazz) || UpdateAnimalRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors e) {
        NewAnimalRequest model = (NewAnimalRequest) target;

        //ValidationUtils.rejectIfEmptyOrWhitespace(e, "nombre", "NotEmpty",new Object[]{"Nombre"});
        if(model.isTieneChip() && UtilString.stringIsEmptyOrNull(model.getChipCode())){
            e.rejectValue("chipCode", "NotEmpty");
        }
        if(model.getRaza() != null && model.getRaza().getId() != null && !razaRepository.existsById(model.getRaza().getId())){
            e.rejectValue("raza", "NoExist");
        }
    }
}

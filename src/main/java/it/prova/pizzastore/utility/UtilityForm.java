package it.prova.pizzastore.utility;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.model.Pizza;

public class UtilityForm {

	public static Pizza initializePizzaFromParams(String nomeParam, String descrizioneParam, String ingredientiParam,
			String prezzoBaseParam) {

		Pizza result = new Pizza(nomeParam, descrizioneParam, ingredientiParam);
		if (NumberUtils.isCreatable(prezzoBaseParam)) {
			result.setPrezzoBase(Integer.parseInt(prezzoBaseParam));
		}
		return result;
	}

	public static boolean validatePizzaBean(Pizza pizzaToBeValidated) {
		if (pizzaToBeValidated == null || StringUtils.isBlank(pizzaToBeValidated.getNome())
				|| StringUtils.isBlank(pizzaToBeValidated.getDescrizione())
				|| StringUtils.isBlank(pizzaToBeValidated.getIngredienti())
				|| pizzaToBeValidated.getPrezzoBase() == null || pizzaToBeValidated.getPrezzoBase() < 1) {
			return false;
		} else {
			return true;
		}
	}
}
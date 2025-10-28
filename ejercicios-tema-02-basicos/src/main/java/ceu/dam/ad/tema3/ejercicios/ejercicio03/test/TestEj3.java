package ceu.dam.ad.tema3.ejercicios.ejercicio03.test;

import java.util.List;
import java.util.Map;
import ceu.dam.ad.tema3.ejercicios.ejercicio04.service.PedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ceu.dam.ad.tema3.ejercicios.ejercicio03.model.Pago;
import ceu.dam.ad.tema3.ejercicios.ejercicio03.service.PagosException;
import ceu.dam.ad.tema3.ejercicios.ejercicio03.service.PagosService;

@Component
public class TestEj3 {

	@Autowired
	private PagosService service;

	public void test() {

		try {
			Map<String, List<Pago>> clientesPagos = service.consultarPagosClientes();

			List<Pago> pagos = clientesPagos.get("MARILYN.ROSS@sakilacustomer.org");
			pagos.forEach(System.out::println);
		} catch (PagosException e) {
			e.printStackTrace();
		}

	}

}

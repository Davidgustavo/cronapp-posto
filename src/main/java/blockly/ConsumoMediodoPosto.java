package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;

@CronapiMetaData(type = "blockly")
@CronappSecurity
public class ConsumoMediodoPosto {

	public static final int TIMEOUT = 300;

	/**
	 *
	 * @return Var
	 */
	// consumoMediodoPosto
	public static Var Consumo_Medio_Posto() throws Exception {
		return new Callable<Var>() {

			private Var Retorno = Var.VAR_NULL;
			private Var Consulta = Var.VAR_NULL;
			private Var valor_parcial = Var.VAR_NULL;

			public Var call() throws Exception {
				Retorno = cronapi.list.Operations.newList();
				Consulta = cronapi.database.Operations.query(Var.valueOf("app.entity.Abastecimento"), Var.valueOf(
						"select a.carro.modelo, a.carro.marca, SUM(a.valor), SUM(a.kmAtual) from Abastecimento a  group by a.carro.modelo, a.carro.marca  order by SUM(a.valor) asc, SUM(a.kmAtual) asc"));
				while (cronapi.database.Operations.hasElement(Consulta).getObjectAsBoolean()) {
					valor_parcial = cronapi.map.Operations.createObjectMapWith(
							Var.valueOf("custo_medio", cronapi.database.Operations.getField(Consulta, Var.VAR_NULL)),
							Var.valueOf("posto", cronapi.database.Operations.getField(Consulta, Var.VAR_NULL)));
					cronapi.list.Operations.addLast(Retorno, valor_parcial);
					cronapi.database.Operations.next(Consulta);
				} // end while
				return Retorno;
			}
		}.call();
	}

	/**
	 *
	 * @return Var
	 */
	// consumoMediodoPosto
	public static Var Consumo_Medio_Posto2() throws Exception {
		return new Callable<Var>() {

			private Var Retorno = Var.VAR_NULL;
			private Var Consulta = Var.VAR_NULL;
			private Var valor_parcial = Var.VAR_NULL;

			public Var call() throws Exception {
				Retorno = cronapi.list.Operations.newList();
				Consulta = cronapi.database.Operations.query(Var.valueOf("app.entity.User"),
						Var.valueOf("select u from User u"));
				while (cronapi.database.Operations.hasElement(Consulta).getObjectAsBoolean()) {
					valor_parcial = cronapi.map.Operations.createObjectMapWith(
							Var.valueOf("custo_medio", cronapi.database.Operations.getField(Consulta, Var.VAR_NULL)),
							Var.valueOf("posto", null));
					cronapi.list.Operations.addLast(Retorno, valor_parcial);
					cronapi.database.Operations.next(Consulta);
				} // end while
				return Retorno;
			}
		}.call();
	}

}

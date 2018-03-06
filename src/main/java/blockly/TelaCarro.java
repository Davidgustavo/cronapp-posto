package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;

@CronapiMetaData(type = "blockly")
@CronappSecurity
public class TelaCarro {

	public static final int TIMEOUT = 300;

	/**
	 *
	 * @param Entidade
	 * @return Var
	 */
	// TelaCarro
	public static Var calculoConsumoMedioCarro(Var Entidade) throws Exception {
		return new Callable<Var>() {

			private Var consultaAbastecimento = Var.VAR_NULL;
			private Var litros = Var.VAR_NULL;
			private Var consumo = Var.VAR_NULL;
			private Var somaConsumo = Var.VAR_NULL;
			private Var quantidadeAbastecimento = Var.VAR_NULL;

			public Var call() throws Exception {
				consultaAbastecimento = cronapi.database.Operations.query(Var.valueOf("app.entity.Abastecimento"),
						Var.valueOf(
								"select a.valor, a.precoLitro, a.km from Abastecimento a where a.carro.id = :carroId"),
						Var.valueOf("carroId", cronapi.object.Operations.getObjectField(Entidade, Var.valueOf("id"))));
				if (cronapi.database.Operations.hasElement(consultaAbastecimento).getObjectAsBoolean()) {
					while (cronapi.database.Operations.hasElement(consultaAbastecimento).getObjectAsBoolean()) {
						litros = cronapi.math.Operations.divisor(
								cronapi.database.Operations.getField(consultaAbastecimento, Var.valueOf("this[0]")),
								cronapi.database.Operations.getField(consultaAbastecimento, Var.valueOf("this[1]")));
						consumo = cronapi.math.Operations.divisor(
								cronapi.database.Operations.getField(consultaAbastecimento, Var.valueOf("this[2]")),
								litros);
						somaConsumo = cronapi.math.Operations.sum(somaConsumo, consumo);
						quantidadeAbastecimento = cronapi.math.Operations.sum(quantidadeAbastecimento, Var.valueOf(1));
						cronapi.database.Operations.next(consultaAbastecimento);
					} // end while
				} else {
					somaConsumo = Var.valueOf(0);
					quantidadeAbastecimento = Var.valueOf(1);
				}
				return cronapi.math.Operations.divisor(somaConsumo, quantidadeAbastecimento);
			}
		}.call();
	}

}

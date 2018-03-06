package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;

@CronapiMetaData(type = "blockly")
@CronappSecurity
public class AbastecCalc {

	public static final int TIMEOUT = 300;

	/**
	 *
	 * @param Entidade
	 * @return Var
	 */
	// Descreva esta função...
	public static Var custoKM(Var Entidade) throws Exception {
		return new Callable<Var>() {

			private Var kmcalculado = Var.VAR_NULL;

			public Var call() throws Exception {
				kmcalculado = cronapi.math.Operations.subtract(
						cronapi.object.Operations.getObjectField(Entidade, Var.valueOf("kmAtual")),
						cronapi.object.Operations.getObjectField(Entidade, Var.valueOf("kmAnterior")));
				return cronapi.math.Operations
						.divisor(cronapi.object.Operations.getObjectField(Entidade, Var.valueOf("valor")), kmcalculado);
			}
		}.call();
	}

}

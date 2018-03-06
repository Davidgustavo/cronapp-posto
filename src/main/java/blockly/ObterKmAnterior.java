package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;

@CronapiMetaData(type = "blockly")
@CronappSecurity
public class ObterKmAnterior {

	public static final int TIMEOUT = 300;

	/**
	 *
	 * @return Var
	 */
	// obterKmAnterior
	public static Var obter_Km_Anterior() throws Exception {
		return new Callable<Var>() {

			private Var idCarro = Var.VAR_NULL;
			private Var validarData = Var.VAR_NULL;
			private Var novoKM = Var.VAR_NULL;

			public Var call() throws Exception {
				idCarro = Var.valueOf(cronapi.screen.Operations.getValueOfField(Var.valueOf("Carro.active.id")));
				validarData = Var
						.valueOf(cronapi.screen.Operations.getValueOfField(Var.valueOf("Abastecimento.active.data")));
				while (Var.valueOf(cronapi.database.Operations.hasElement(idCarro)).getObjectAsBoolean()) {
					if (Var.valueOf(idCarro.equals(cronapi.list.Operations.newList(cronapi.database.Operations
							.query(Var.valueOf("app.entity.Carro"), Var.valueOf("select c.placa from Carro c")))))
							.getObjectAsBoolean()) {
						while (Var.valueOf(cronapi.database.Operations.hasElement(validarData)).getObjectAsBoolean()) {
							if (Var.valueOf(validarData.compareTo(cronapi.list.Operations
									.newList(cronapi.database.Operations.query(Var.valueOf("app.entity.Abastecimento"),
											Var.valueOf(
													"select a.data from Abastecimento a where a.carro.placa = :consultarPlaca"),
											Var.valueOf("consultarPlaca", idCarro)))) > 0)
									.getObjectAsBoolean()) {
								novoKM = cronapi.database.Operations.query(Var.valueOf("app.entity.Abastecimento"),
										Var.valueOf(
												"select a.kmAtual from Abastecimento a where a.carro.placa = :outraPlaca"),
										Var.valueOf("outraPlaca", idCarro));
								cronapi.util.Operations.callClientFunction(
										Var.valueOf("cronapi.screen.changeValueOfField"),
										Var.valueOf("Abastecimento.active.kmAnterior"), novoKM);
							} else {
								cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.notify"),
										Var.valueOf("warning"), Var.valueOf("Veiculo\n informado não existe!!"));
							}
						} // end while
					} else {
						cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.notify"),
								Var.valueOf("warning"), Var.valueOf("Veiculo\n informado não existe!!"));
					}
				} // end while
				return Var.VAR_NULL;
			}
		}.call();
	}

	/**
	 *
	 * @return Var
	 */
	// obterKmAnterior
	public static Var obter_Km_Anterior2() throws Exception {
		return new Callable<Var>() {

			private Var idCarro = Var.VAR_NULL;
			private Var listaAbastecimento = Var.VAR_NULL;
			private Var ultimoAbastecimento = Var.VAR_NULL;

			public Var call() throws Exception {
				idCarro = Var.valueOf(cronapi.screen.Operations.getValueOfField(Var.valueOf("Carro.active.id")));
				listaAbastecimento = cronapi.database.Operations.query(Var.valueOf("app.entity.Abastecimento"),
						Var.valueOf(
								"select a.kmAtual from Abastecimento a where a.carro.id = :carroId   order by a.data asc"),
						Var.valueOf("carroId", idCarro));
				ultimoAbastecimento = cronapi.list.Operations.getFirst(listaAbastecimento);
				if (Var.valueOf(idCarro.equals(Var.VAR_NULL)).getObjectAsBoolean()) {
					cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.notify"),
							Var.valueOf("error"), Var.valueOf("Veículo não existe!"));
				} else {
					cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.changeValueOfField"),
							Var.valueOf("Abastecimento.active.kmAnterior"),
							cronapi.list.Operations.getFirst(listaAbastecimento));
				}
				return Var.VAR_NULL;
			}
		}.call();
	}

}

window.blockly = window.blockly || {};
window.blockly.js = window.blockly.js || {};
window.blockly.js.blockly = window.blockly.js.blockly || {};
window.blockly.js.blockly.TeladeAbestecimento = window.blockly.js.blockly.TeladeAbestecimento
		|| {};

var litros;

/**
 * TeladeAbestecimento
 */
window.blockly.js.blockly.TeladeAbestecimento.validarqtdeLitros = function() {
	litros = this.cronapi.screen.getValueOfField("Abastecimento.active.valor")
			/ this.cronapi.screen
					.getValueOfField("Abastecimento.active.precoLitro");
	if (litros >= 200) {
		this.cronapi.screen
				.notify('error', 'Quantidade de litros max é de 199');
		this.cronapi.screen.hideComponent("salvar");
	} else {
		this.cronapi.screen.showComponent("salvar");
	}
}

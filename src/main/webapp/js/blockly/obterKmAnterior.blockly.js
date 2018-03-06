window.blockly = window.blockly || {};
window.blockly.js = window.blockly.js || {};
window.blockly.js.blockly = window.blockly.js.blockly || {};
window.blockly.js.blockly.ObterKmAnterior = window.blockly.js.blockly.ObterKmAnterior
		|| {};

var dataValida;

/**
 * obterKmAnterior
 */
window.blockly.js.blockly.ObterKmAnterior.obter_Km_Anterior = function() {
	dataValida = this.cronapi.screen.getValueOfField("");
}

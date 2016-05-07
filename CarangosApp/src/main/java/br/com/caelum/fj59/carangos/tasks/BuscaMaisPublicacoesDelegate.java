package br.com.caelum.fj59.carangos.tasks;

import br.com.caelum.fj59.carangos.modelo.Publicacao;

import java.util.List;

/**
 * Created by felipeweb on 5/7/16.
 */
public interface BuscaMaisPublicacoesDelegate {
	void lidaComRetorno(List<Publicacao> retorno);
	void lidaComErro(Exception e);
}

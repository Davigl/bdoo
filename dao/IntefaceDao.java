package dao;

import java.util.List;

public interface IntefaceDao<T> {
	
	public void criar(T object);
	
	public T ler(T object);
	
	public List<T> lerTudo();
	
	public void atualizar(T object);
	
	public void excluir(T object);	

}

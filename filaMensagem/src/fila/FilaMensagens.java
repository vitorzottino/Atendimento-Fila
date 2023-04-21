package fila;

import entidades.Mensagem;

public class FilaMensagens {

	int N = 5;
	Mensagem dados[] = new Mensagem[N];
	int ini, fim, count;

	public void init() {
		ini = fim = 0;

	}

	public boolean isEmpty() {
		if (count == 0)
			return true;
		else
			return false;
	}

	public boolean isFull() {
		if (count == N)
			return true;
		else
			return false;
	}

	public void enqueue(Mensagem msg) {
		if (isFull())
			System.out.println("Fila Cheia, impossivel inserir");
		else {
			dados[fim] = msg;
			fim += 1;
			count++;
		}

	}

	public Mensagem dequeue() {
		Mensagem msgStr = dados[ini];
		ini = (ini + 1) % N;
		count--;
		return msgStr;

	}

	public void clean() {
		if (!isEmpty()) {
			ini = 0;
			fim = 0;
			count = 0;
		}
	}

	public int size() {
		return count;
	}
	

}

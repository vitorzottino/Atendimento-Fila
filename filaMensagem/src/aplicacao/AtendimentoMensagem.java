package aplicacao;

import java.util.Scanner;
import entidades.Mensagem;
import fila.FilaMensagens;
/*
 * NOMES MEMBROS GRUPO
 * Enzo Fernandes Pavanello
 * Ilan Arnaut Eschiezaro	
 * Leonardo Rigo Rezende Recco Cardoso
 * Raphael Augusto Pereira Cruz
 * Vitor Cassemiro Ferreira Zottino
 */
public class AtendimentoMensagem {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		FilaMensagens filaMensagem = new FilaMensagens();
		FilaMensagens filaReclamacao = new FilaMensagens();
		FilaMensagens filaSugestao = new FilaMensagens();
		FilaMensagens filaResolucao = new FilaMensagens();

		int opcao, opcaoAux, opcaoAux2;
		int opcaoMenu = 0;
		String contato, msgText, nome;
		int motivo;

		do {
			if (filaMensagem.size() > 0)	
				System.out.println("\n" + filaMensagem.size() + " Mensagem(ns) nao lidas");
			System.out.println("\nMENU INICIAL");
			System.out.println("1 - Recebimento de Mensagem \n2 - Atendimento Mensagem  \n0 - Encerrar atendimento");
			opcao = input.nextInt();

			switch (opcao) {
			case 0:
				if (filaMensagem.isEmpty()) {
					System.out.println("Encerrando o atendimento");
				} else {
					System.out.println("Ainda existe mensagens na fila!");
					opcao = -1;
				}
				break;
			case 1:
				Mensagem msg = new Mensagem();

				System.out.println("Deseja informar seu nome? ");
				System.out.println("1- sim\t 2- nao");
				int opcaoNome = input.nextInt();
				if (opcaoNome == 1) {
					System.out.print("Informe seu nome: ");
					input.nextLine();
					nome = input.nextLine();
					msg.nome = nome;
				}

				do {
					System.out.print("Informe seu email/telefone: ");
					contato = input.next();
					msg.contato = contato;
					System.out.println("Informe o motivo do contato\n1- Reclamacao \n2- Sugestao");
					motivo = input.nextInt();
					msg.motivoContato = motivo;

					System.out.print("Digite a mensagem: ");
					input.nextLine();
					msgText = input.nextLine();
					msg.msgText = msgText;
					filaMensagem.enqueue(msg);

					if (motivo == 1) {
						filaReclamacao.enqueue(msg);
						msg.motivoContatoStr = "Reclamacao";
					} else {
						filaSugestao.enqueue(msg);
						msg.motivoContatoStr = "Sugestao";
					}

				} while (contato == " " || motivo == 0 || msgText == null);

				System.out.print("Mensagem cadastrada no sistema\nDeseja voltar ao menu?\n1- sim\n2- nao\n");
				opcaoAux = input.nextInt();
				if (opcaoAux == 2) {
					if(!filaMensagem.isEmpty()) {
						System.out.println("Ainda há mensagens na fila\n");
						opcao = -1;
					}else {
					System.out.print("Encerrando");
					opcao = 0;
					}
				}

				break;

			case 2:

				do {
					System.out.println("\n----------SERVIÇO DE ATENDIMENTO----------");
					System.out.println("Reclamacoes: " + filaReclamacao.size() + " Sugestoes: " + filaSugestao.size() + " Resolucoes: " + filaResolucao.size());
					System.out.println("Favor indicar fila desejada (1/2/3): ");
					System.out.println("1- Reclamacoes \n2- Sugestoes \n3- Resoluoees \n0- Voltar");
					opcaoAux = input.nextInt();

					switch (opcaoAux) {
					
					case 0:
						opcaoMenu = 1;
						
						
					break;
						

					case 1:
						if (!filaReclamacao.isEmpty()) {
							System.out.println("Favor informar a acao referente a tratamento - RECLAMACAO"
									+ "\n1 - Resolucao da solicitacao"
									+ "\n2 - Transferir para a Fila de Resolucao");
							opcaoAux2 = input.nextInt();
							if (opcaoAux2 == 1) {
								System.out.println(filaReclamacao.dequeue().toString() + "\n----------Reclamacao Resolvida----------");
								System.out.println("Enviada resposta para cliente: "
										+ "sua solicitacao ja foi resolvida. Obrigado!!!");
								filaMensagem.dequeue();
						}
							else {
								filaResolucao.enqueue(filaReclamacao.dequeue());
								System.out.println("Reclamacao encaminhada para fila de resolucao");
								System.out.println("Em breve recebera uma resposta!");
							}

						} else
							System.out.println("Fila de Reclamacoes Vazia");
						break;
					case 2:
						if (!filaSugestao.isEmpty()) {
							System.out.println("Favor informar a acao referente a tratamento - SUGESTAO"
									+ "\n1 - Resolucao da solicitacao"
									+ "\n2 - Transferir para a Fila de Resolucao");
							opcaoAux2 = input.nextInt();
							if (opcaoAux2 == 1) {
								System.out.println(filaSugestao.dequeue().toString() + "\n----------Sugestão Resolvida----------");
								System.out.println("Enviada resposta para cliente: "
										+ "sua solicitacao ja foi resolvida. Obrigado!!!");
								filaMensagem.dequeue();

							}
							else {
								filaResolucao.enqueue(filaSugestao.dequeue());
								System.out.println("Sugestao encaminhada para fila de resolucao");
								System.out.println("Em breve recebera uma resposta!");
							}

						} else
							System.out.println("Fila de Sugestoes Vazia");
						break;
					case 3:
						if (!filaResolucao.isEmpty()) {
							System.out.println(filaResolucao.dequeue().toString() + "\n----------Resolvido----------");
							System.out.println("Enviada resposta para cliente: sua "
									+ "solicitacao já foi resolvida pelo setor responsavel. Obrigado!!!");
							filaMensagem.dequeue();
						}
						else
							System.out.println("Fila de Resolucoes Vazia");

						break;

					default:
						System.out.println("Valor Invalido");
					}

				} while (opcaoMenu != 1 && !filaMensagem.isEmpty());

			default:
				System.out.println("\n");
			}
		} while (opcao != 0);

		input.close();
	}
}

package com.nathancunha.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nathancunha.cursomc.domain.Categoria;
import com.nathancunha.cursomc.domain.Cidade;
import com.nathancunha.cursomc.domain.Cliente;
import com.nathancunha.cursomc.domain.Endereco;
import com.nathancunha.cursomc.domain.Estado;
import com.nathancunha.cursomc.domain.Pagamento;
import com.nathancunha.cursomc.domain.PagamentoComBoleto;
import com.nathancunha.cursomc.domain.PagamentoComCartao;
import com.nathancunha.cursomc.domain.Pedido;
import com.nathancunha.cursomc.domain.Produto;
import com.nathancunha.cursomc.domain.enums.EstadoPagamento;
import com.nathancunha.cursomc.domain.enums.TipoCliente;
import com.nathancunha.cursomc.repositories.CategoriaRepository;
import com.nathancunha.cursomc.repositories.CidadeRepository;
import com.nathancunha.cursomc.repositories.ClienteRepository;
import com.nathancunha.cursomc.repositories.EnderecoRepository;
import com.nathancunha.cursomc.repositories.EstadoRepository;
import com.nathancunha.cursomc.repositories.PagamentoRepository;
import com.nathancunha.cursomc.repositories.PedidoRepository;
import com.nathancunha.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository; 
	
	@Autowired
	private PedidoRepository pedidoRepository; 
	
	@Autowired
	private PagamentoRepository pagamentoRepository; 
	
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		Estado estado1 = new Estado(null,"Minas Gerais");
		Estado estado2 = new Estado(null,"São Paulo");
		
		Cidade cidade1 = new Cidade(null,"Uberlândia",estado1);
		Cidade cidade2 = new Cidade(null,"São Paulo",estado2);
		Cidade cidade3 = new Cidade(null,"Campinas",estado2);
		
		estado1.getCidades().addAll(Arrays.asList(cidade1));
		estado2.getCidades().addAll(Arrays.asList(cidade2, cidade3));
		
		estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2,cidade3));
		
		Cliente cli1 = new Cliente(null,"Maria Silva","maria@nogmail.com", "41084144034", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("37107433","981582550"));
		
		Endereco end1 = new Endereco(null, "Rua Xenofonte", "173", "ap 555", "Jardim Graziela", "86036-090", cli1 , cidade1);
		
		Endereco end2 = new Endereco(null, "Avenida Matos", "173", "ap 555", "Centro", "86036-080", cli1 , cidade2);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1,end2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1,end2));
		
		instanciaCategoriaProduto();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, end2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
	}

	private void instanciaCategoriaProduto() {
		Categoria categoria1 = new Categoria(null, "Informática");
		Categoria categoria2 = new Categoria(null, "Escritório");

		Produto prod1 = new Produto(null, "Computador", 2000.00);
		Produto prod2 = new Produto(null, "Impressora", 800.00);
		Produto prod3 = new Produto(null, "Mouse", 80.00);

		categoria1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
		categoria2.getProdutos().addAll(Arrays.asList(prod2));

		prod1.getCategorias().addAll(Arrays.asList(categoria1));

		prod2.getCategorias().addAll(Arrays.asList(categoria1, categoria2));
		prod3.getCategorias().addAll(Arrays.asList(categoria1));

		categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2));
		produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3));
	}

}

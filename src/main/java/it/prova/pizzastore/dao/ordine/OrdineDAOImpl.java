package it.prova.pizzastore.dao.ordine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.auth.StatoUtente;

public class OrdineDAOImpl implements OrdineDAO {
	private EntityManager entityManager;

	@Override
	public List<Ordine> list() throws Exception {
		return entityManager.createQuery("from Ordine", Ordine.class).getResultList();
	}

	@Override
	public Optional<Ordine> findOne(Long id) throws Exception {
		Ordine result = entityManager.find(Ordine.class, id);
		return result != null ? Optional.of(result) : Optional.empty();
	}

	@Override
	public void update(Ordine input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.merge(input);
	}

	@Override
	public void insert(Ordine input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.merge(input);
	}

	@Override
	public void delete(Ordine input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		input.setCloded(true);
		entityManager.merge(input);
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Ordine> findByExample(Ordine example) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select o from Ordine o where o.id = o.id ");

		if (StringUtils.isNotEmpty(example.getCodice())) {
			whereClauses.add(" o.codice  like :codice ");
			paramaterMap.put("codice", "%" + example.getCodice() + "%");
		}
		if (example.getData() != null) {
			whereClauses.add(" o.data >= :data ");
			paramaterMap.put("data", example.getData());
		}
		if (example.getCostoTotaleOrdine() != null && example.getCostoTotaleOrdine() > 0) {
			whereClauses.add(" o.costoTotaleOrdine = :costoTotaleOrdine ");
			paramaterMap.put("costoTotaleOrdine", example.getCostoTotaleOrdine());
		}
		if (example.getCliente() != null && example.getCliente().getId() != null && example.getCliente().getId() > 0
				&& example.getCliente().isAttivo()) {
			whereClauses.add(" o.cliente_id = :cliente_id ");
			paramaterMap.put("cliente_id", example.getCliente().getId());
		}
		if (example.getUtente() != null && example.getUtente().getId() != null && example.getUtente().getId() > 0
				&& example.getUtente().getStato() == StatoUtente.ATTIVO) {
			whereClauses.add(" o.utente_id = :utente_id ");
			paramaterMap.put("utente_id", example.getUtente().getId());
		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));

		TypedQuery<Ordine> typedQuery = entityManager.createQuery(queryBuilder.toString(), Ordine.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

	@Override
	public Integer getSumPrezziPizze(Ordine ordineInstance) {
		TypedQuery<Long> typedQuery = entityManager.createQuery(
				"select sum(p.prezzoBase) from Ordine o join o.pizze p where o.codice = :codice", Long.class);
		return typedQuery.setParameter("codice", ordineInstance.getCodice()).getSingleResult().intValue();
	}

	@Override
	public Optional<Ordine> findOneEager(long parseLong) {
		return entityManager.createQuery("select o from Ordine o left join fetch o.cliente c left join fetch o.utente u where o.id=:idOrdine", Ordine.class)
				.setParameter("idOrdine", parseLong).getResultList().stream().findFirst();
	}

}

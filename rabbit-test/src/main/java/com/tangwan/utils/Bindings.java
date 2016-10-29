package com.tangwan.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;

public class Bindings {

	private Map<String, DirectExchange> exchanges = new HashMap<String, DirectExchange>();
	private Map<String, Queue> queues = new HashMap<String, Queue>();
	private Set<String> binded = new HashSet<String>();
	private RabbitAdmin rabbitAdmin;

	public Set<String> getBinded() {
		return binded;
	}

	public Map<String, Queue> getQueues() {
		return queues;
	}

	public Bindings(RabbitAdmin rabbitAdmin) {
		this.rabbitAdmin = rabbitAdmin;
	}

	void add(String exchangeName, String queueName) {
		binded.add(exchangeName + "|" + queueName);
	}

	void destroy() {
		exchanges.clear();
		queues.clear();
		binded.clear();
	}

	boolean beBinded(String exchangeName, String queueName) {
		return binded.contains(exchangeName + "|" + queueName);
	}

	synchronized void declareBinding(String exchangeName, String queueName) {
		String bindRelation = exchangeName + "|" + queueName;
		// if (binded.contains(bindRelation))
		// return;

		boolean needBinding = false;
		DirectExchange directExchange = exchanges.get(exchangeName);
		if (directExchange == null) {
			directExchange = new DirectExchange(exchangeName, true, false, null);
			exchanges.put(exchangeName, directExchange);
			rabbitAdmin.declareExchange(directExchange);
			needBinding = true;
		}

		Queue queue = queues.get(queueName);
		if (queue == null) {
			queue = new Queue(queueName, true, false, false);
			queues.put(queueName, queue);
			rabbitAdmin.declareQueue(queue);
			needBinding = true;
		}

		if (needBinding) {
			Binding binding = BindingBuilder.bind(queue).to(directExchange).with(queueName);
			rabbitAdmin.declareBinding(binding);
			binded.add(bindRelation);
		}
	}

}

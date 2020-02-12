package com.consumer.service.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.consumer.service.entity.ItemEntity;
import com.consumer.service.entity.ProductEntity;
import com.consumer.service.serviceImpl.ConsumerService;

@Controller
@RequestMapping(value = "consume")
public class ConsumerController {

	protected Logger logger = Logger.getLogger(ConsumerController.class.getName());

	@Autowired
	private ConsumerService consumerService;

	@RequestMapping(value = "/productList", method = RequestMethod.GET)
	public String productList(ModelMap model) {
		model.put("listProducts", consumerService.getAllProducts());
		return "product/ListInventory";
	}

	@RequestMapping(value = "/products/{productId}", method = RequestMethod.GET)
	public String productById(@PathVariable("productId") Long productId, HttpSession session) {

		List<ItemEntity> itemEntityList = new ArrayList<ItemEntity>();

		if (session.getAttribute("item") == null) {
			itemEntityList = new ArrayList<ItemEntity>();

			ProductEntity productEntity = consumerService.getProductById(productId);
			ItemEntity itemEntity = new ItemEntity(productEntity, 1);
			itemEntityList.add(itemEntity);

			session.setAttribute("item", itemEntityList);
		} else {
			itemEntityList = (List<ItemEntity>) session.getAttribute("item");

			int index = this.ifProductExists(productId, itemEntityList);
			if (index == -1) {
				ProductEntity productEntity = consumerService.getProductById(productId);
				ItemEntity itemEntity = new ItemEntity(productEntity, 1);
				itemEntityList.add(itemEntity);
			} else {
				int quantity = itemEntityList.get(index).getProductQuantity() + 1;
				itemEntityList.get(index).setProductQuantity(quantity);
			}
			session.setAttribute("item", itemEntityList);
		}
		return "item/Selection";
	}

	@RequestMapping(value = "/removeProduct/{productId}", method = RequestMethod.GET)
	public String removeProdById(@PathVariable("productId") Long productId, HttpSession session) {
		List<ItemEntity> itemEntityList = (List<ItemEntity>) session.getAttribute("item");
		int index = this.ifProductExists(productId, itemEntityList);
		itemEntityList.remove(index);
		session.setAttribute("item", itemEntityList);
		return "item/Selection";
	}

	private int ifProductExists(Long id, List<ItemEntity> itemEntityList) {
		for (int i = 0; i < itemEntityList.size(); i++) {
			if (itemEntityList.get(i).getProduct().getProductId().equals(id)) {
				return i;
			}
		}
		return -1;
	}

}

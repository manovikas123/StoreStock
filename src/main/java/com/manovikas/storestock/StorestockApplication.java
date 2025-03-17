package com.manovikas.storestock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StorestockApplication {

	public static void main(String[] args) {
		SpringApplication.run(StorestockApplication.class, args);

	}
/*
	@Bean
	public CommandLineRunner commandLineRunner(StockServiceImp ser)
	{

		return runner ->{
			//createItems(storeDAO);
			//createBrands(storeDAO);
			//createPq(storeDAO);
			//createAll(storeDAO);

			//deletePq(storeDAO);

			//deleteBrand(storeDAO);
			//deleteItem(storeDAO);
			//create(ir);
			//listofitems(ser);
		};
	}

	private void listofitems(StockServiceImp ser) {

		List<Items> list=ser.getAllItems();
		System.out.println("the list is");
		for(Items i :list) {
			System.out.println(i.getId()+" "+i.getItemName());
		}
		System.out.println("Done");
	}


	private void create(ItemRepository ir)
	{
		int id=3;
		System.out.println("finding");


		System.out.println("done");
	}
	private void deleteItem(StoreDAO storeDAO) {
		int id=2;
		System.out.println("deleting brand");
		storeDAO.DeleteItem(id);
		System.out.println("done");
	}

	private void deleteBrand(StoreDAO storeDAO) {

		int id=9;
		System.out.println("deleting brand");
		storeDAO.DeleteBrand(id);
		System.out.println("done");
	}

	private void deletePq(StoreDAO storeDAO) {
		int id=10;
		System.out.println("deleting");
		storeDAO.DeletePriceAndQuantity(10);
		System.out.println("done");
	}

	private void createAll(StoreDAO storeDAO) {

		Items i=new Items("books123");
		Brand b=new Brand("123classmates");
		PriceQuantity pq=new PriceQuantity(45,20);
		i.addBrandAndPQ(b,pq);
		storeDAO.createItem(i);

	}

	private void createPq(StoreDAO storeDAO) {
		System.out.println("creating pq");
		Brand b=storeDAO.findBrandById(4);
		System.out.println("brand is"+b.toString());
		PriceQuantity pq=new PriceQuantity(10,5);

		pq.setBrand_id(b);
		storeDAO.createPriceAndQuantityWithBrand(pq);
		System.out.println("brand id in pq is  "+pq.getBrand_id());
		System.out.println("done");
	}


	private void createBrands(StoreDAO storeDAO) {


		System.out.println("creating brand");
		Items i=storeDAO.findItemsById(2);
		Brand brand=new Brand("diarymilk_silk");
		brand.setItem_id(i);
		storeDAO.createBrandWithItem(brand);
		System.out.println("done");
	}

	private void createItems(StoreDAO storeDAO) {
		System.out.println("creating item");
		Items item=new Items("biscuits");
		storeDAO.createItem(item);
		System.out.println("done");
	}

*/
}

package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		
		List<Product> list = new ArrayList<>();
		
		String fileStr = "C:\\file\\file.txt";
		File file = new File(fileStr);
		String folderStr = file.getParent();
		
		boolean success = new File(folderStr + "\\out").mkdir();
		
		String targetFileStr = folderStr + "\\out\\summary.csv";
		
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))){
			String readItem = br.readLine();
			
			while (readItem != null) {
				String[] item = readItem.split(",");
				String name = item[0];
				double price = Double.parseDouble(item[1]);
				int quantity = Integer.parseInt(item[2]);
				
				list.add(new Product(name, price, quantity));
				
				readItem = br.readLine();
			}
			
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))){
				for (Product product : list) {
					bw.write(product.getName() + "," + product.totalPrice());
					bw.newLine();
				}
				System.out.println(targetFileStr = " CREATED!");
				
			}
			catch(IOException e){
				e.getMessage();				
			}
		}
		catch (IOException e){
			System.out.println(e.getMessage());
		}

		for (Product product : list) {
			System.out.println(product.getName() + "\n" + product.getPrice() + "\n" + product.getQuantity() + "\n" + product.totalPrice());
		}
		
	}

}

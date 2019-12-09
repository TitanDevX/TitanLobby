package me.titan.titanlobby.itemCreator;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.remain.CompColor;
import org.mineacademy.fo.remain.CompMaterial;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TitanItemCreator  {

	final CompMaterial material;

	String name;
	List<String> lores = new ArrayList<>();
	int data;
	int amount;
	boolean glow;


	public TitanItemCreator(CompMaterial material) {
		this.material = material;
	}
	public TitanItemCreator(CompMaterial material, String name) {
		this.material = material;
		this.name = name;
	}
	public TitanItemCreator(CompMaterial material, String name, String... lores) {
		this(material, name);
		for(String lore : lores){
			this.lores.add(lore);
		}
	}
	public TitanItemCreator data(int data){
		this.data = data;

		return this;
	}
	public TitanItemCreator color(CompColor color){
		if(color == null) return this;
		return data(color.getDye().getWoolData());
	}
	public TitanItemCreator amount(int amount){
		this.setAmount(amount);

		return this;
	}
	public TitanItemCreator glow(boolean glow){
		this.glow = glow;

		return this;
	}
	public TitanItemCreator lores(List<String> lores){
		this.lores = lores;

		return this;
	}
	public ItemStack make(){
		ItemStack is;
		if(data != 0) {
			 is = new ItemStack(material.getMaterial(), amount, (short) 0, (byte) data);
		}else {
			return ItemCreator.of(material, name ).lores(lores).glow(glow).amount(amount).build().makeMenuTool();
		}
		return ItemCreator.of(is).glow(glow).material(material).lores(lores).name(name).amount(amount).build().make();
	}



}

package DataStructures.Array.Structures;

import static java.lang.Math.*;

import references.references.*;
import static references.references.references.*;

import static arrays.arrays.arrays.*;


import static DataStructures.Array.Arrays.Arrays.*;

public class Structures{
	public static Data CreateArrayData(){
		Data data;

		data = new Data();
		data.isArray = true;
		data.array = CreateArray();

		return data;
	}

	public static Data CreateStructData(){
		Data data;

		data = new Data();
		data.isStruture = true;
		data.structure = CreateStructure();

		return data;
	}

	public static Structure CreateStructure(){
		Structure st;

		st = new Structure();
		st.keys = CreateArray();
		st.values = CreateArray();

		return st;
	}

	public static Data CreateNumberData(double n){
		Data data;

		data = new Data();
		data.isNumber = true;
		data.number = n;

		return data;
	}

	public static Data CreateBooleanData(boolean b){
		Data data;

		data = new Data();
		data.isBoolean = true;
		data.booleanx = b;

		return data;
	}

	public static Data CreateStringData(char [] string){
		Data data;

		data = new Data();
		data.isString = true;
		data.string = string;

		return data;
	}

	public static Data CreateNoTypeData(){
		Data data;

		data = new Data();
		data.isStruture = false;
		data.isArray = false;
		data.isNumber = false;
		data.isBoolean = false;
		data.isString = false;

		return data;
	}

	public static void AddStructToArray(Array ar, Structure st){
		Data data;

		data = CreateStructData();
		delete(data.structure);
		data.structure = st;

		ArrayAdd(ar, data);
	}

	public static void AddArrayToArray(Array ar, Array ar2){
		Data data;

		data = CreateArrayData();
		delete(data.array);
		data.array = ar2;

		ArrayAdd(ar, data);
	}

	public static void AddNumberToArray(Array ar, double n){
		ArrayAdd(ar, CreateNumberData(n));
	}

	public static void AddBooleanToArray(Array ar, boolean b){
		ArrayAdd(ar, CreateBooleanData(b));
	}

	public static void AddStringToArray(Array ar, char [] str){
		ArrayAdd(ar, CreateStringData(str));
	}

	public static void AddDataToArray(Array ar, Data data){
		ArrayAdd(ar, data);
	}

	public static double StructKeys(Structure st){
		return ArrayLength(st.keys);
	}

	public static boolean StructHasKey(Structure st, char [] key){
		double i;
		boolean hasKey;

		hasKey = false;
		for(i = 0d; i < StructKeys(st); i = i + 1d){
			if(StringsEqual(st.keys.array[(int)(i)].string, key)){
				hasKey = true;
			}
		}

		return hasKey;
	}

	public static double StructKeyIndex(Structure st, char [] key){
		double i;
		double index;

		index = -1d;
		for(i = 0d; i < StructKeys(st); i = i + 1d){
			if(StringsEqual(st.keys.array[(int)(i)].string, key)){
				index = i;
			}
		}

		return index;
	}

	public static StringReference [] GetStructKeys(Structure st){
		StringReference [] keys;
		double nr, i;

		nr = StructKeys(st);

		keys = new StringReference [(int)(nr)];

		for(i = 0d; i < nr; i = i + 1d){
			keys[(int)(i)] = new StringReference();
			keys[(int)(i)].string = CopyString(st.keys.array[(int)(i)].string);
		}

		return keys;
	}

	public static Structure GetStructFromStruct(Structure st, char [] key){
		double i;
		Structure r;

		r = new Structure();
		for(i = 0d; i < ArrayLength(st.keys); i = i + 1d){
			if(StringsEqual(st.keys.array[(int)(i)].string, key)){
				r = st.values.array[(int)(i)].structure;
			}
		}

		return r;
	}

	public static Array GetArrayFromStruct(Structure st, char [] key){
		double i;
		Array r;

		r = new Array();
		for(i = 0d; i < ArrayLength(st.keys); i = i + 1d){
			if(StringsEqual(st.keys.array[(int)(i)].string, key)){
				r = st.values.array[(int)(i)].array;
			}
		}

		return r;
	}

	public static double GetNumberFromStruct(Structure st, char [] key){
		double i, r;

		r = 0d;
		for(i = 0d; i < ArrayLength(st.keys); i = i + 1d){
			if(StringsEqual(st.keys.array[(int)(i)].string, key)){
				r = st.values.array[(int)(i)].number;
			}
		}

		return r;
	}

	public static boolean GetBooleanFromStruct(Structure st, char [] key){
		double i;
		boolean r;

		r = false;
		for(i = 0d; i < ArrayLength(st.keys); i = i + 1d){
			if(StringsEqual(st.keys.array[(int)(i)].string, key)){
				r = st.values.array[(int)(i)].booleanx;
			}
		}

		return r;
	}

	public static char [] GetStringFromStruct(Structure st, char [] key){
		double i;
		char [] r;

		r = "".toCharArray();
		for(i = 0d; i < ArrayLength(st.keys); i = i + 1d){
			if(StringsEqual(st.keys.array[(int)(i)].string, key)){
				r = st.values.array[(int)(i)].string;
			}
		}

		return r;
	}

	public static Data GetDataFromStruct(Structure st, char [] key){
		double i;
		Data r;

		r = new Data();
		for(i = 0d; i < ArrayLength(st.keys); i = i + 1d){
			if(StringsEqual(st.keys.array[(int)(i)].string, key)){
				delete(r);
				r = st.values.array[(int)(i)];
			}
		}

		return r;
	}

	public static Data GetDataFromStructWithCheck(Structure st, char [] key, BooleanReference foundRef){
		double i;
		Data r;

		r = new Data();
		foundRef.booleanValue = false;
		for(i = 0d; i < ArrayLength(st.keys); i = i + 1d){
			if(StringsEqual(st.keys.array[(int)(i)].string, key)){
				delete(r);
				foundRef.booleanValue = true;
				r = st.values.array[(int)(i)];
			}
		}

		return r;
	}

	public static void AddStructToStruct(Structure st, char [] key, Structure struct){
		double i;

		if(StructHasKey(st, key)){
			i = StructKeyIndex(st, key);
			delete(st.values.array[(int)(i)].structure);
			st.values.array[(int)(i)].structure = struct;
		}else{
			AddStringToArray(st.keys, key);
			AddStructToArray(st.values, struct);
		}
	}

	public static void AddArrayToStruct(Structure st, char [] key, Array ar){
		double i;

		if(StructHasKey(st, key)){
			i = StructKeyIndex(st, key);
			delete(st.values.array[(int)(i)].array);
			st.values.array[(int)(i)].array = ar;
		}else{
			AddStringToArray(st.keys, key);
			AddArrayToArray(st.values, ar);
		}
	}

	public static void AddNumberToStruct(Structure st, char [] key, double n){
		double i;

		if(StructHasKey(st, key)){
			i = StructKeyIndex(st, key);
			st.values.array[(int)(i)].number = n;
		}else{
			AddStringToArray(st.keys, key);
			AddNumberToArray(st.values, n);
		}
	}

	public static void AddBooleanToStruct(Structure st, char [] key, boolean b){
		double i;

		if(StructHasKey(st, key)){
			i = StructKeyIndex(st, key);
			st.values.array[(int)(i)].booleanx = b;
		}else{
			AddStringToArray(st.keys, key);
			AddBooleanToArray(st.values, b);
		}
	}

	public static void AddStringToStruct(Structure st, char [] key, char [] value){
		double i;

		if(StructHasKey(st, key)){
			i = StructKeyIndex(st, key);
			delete(st.values.array[(int)(i)].string);
			st.values.array[(int)(i)].string = value;
		}else{
			AddStringToArray(st.keys, key);
			AddStringToArray(st.values, value);
		}
	}

	public static void AddDataToStruct(Structure st, char [] key, Data data){
		double i;

		if(StructHasKey(st, key)){
			i = StructKeyIndex(st, key);
			FreeData(st.values.array[(int)(i)]);
			st.values.array[(int)(i)] = data;
		}else{
			AddStringToArray(st.keys, key);
			AddDataToArray(st.values, data);
		}
	}

	public static void FreeData(Data data){
		double i;
		Structure st;

		if(data.isStruture){
			st = data.structure;
			for(i = 0d; i < StructKeys(st); i = i + 1d){
				FreeData(ArrayIndex(st.keys, i));
				FreeData(ArrayIndex(st.values, i));
			}
			delete(st);
		}else if(data.isArray){
			FreeArray(data.array);
		}

		delete(data);
	}

	public static void FreeArray(Array array){
		double i;

		for(i = 0d; i < ArrayLength(array); i = i + 1d){
			FreeData(array.array[(int)(i)]);
		}

		delete(array.array);
		delete(array);
	}

	public static boolean DataTypeEquals(Data a, Data b){
		boolean equal;

		equal = true;
		equal = equal && a.isStruture == b.isStruture;
		equal = equal && a.isArray == b.isArray;
		equal = equal && a.isNumber == b.isNumber;
		equal = equal && a.isBoolean == b.isBoolean;
		equal = equal && a.isString == b.isString;

		return equal;
	}

	public static boolean IsStructure(Data a){
		boolean isStructure;

		isStructure = a.isStruture;
		if(a.isArray || a.isNumber || a.isBoolean || a.isString){
			isStructure = false;
		}

		return isStructure;
	}

	public static boolean IsArray(Data a){
		boolean isArray;

		isArray = a.isArray;
		if(a.isStruture || a.isNumber || a.isBoolean || a.isString){
			isArray = false;
		}

		return isArray;
	}

	public static boolean IsNumber(Data a){
		boolean isNumber;

		isNumber = a.isNumber;
		if(a.isStruture || a.isArray || a.isBoolean || a.isString){
			isNumber = false;
		}

		return isNumber;
	}

	public static boolean IsBoolean(Data a){
		boolean isBoolean;

		isBoolean = a.isBoolean;
		if(a.isStruture || a.isArray || a.isNumber || a.isString){
			isBoolean = false;
		}

		return isBoolean;
	}

	public static boolean IsString(Data a){
		boolean isString;

		isString = a.isString;
		if(a.isStruture || a.isArray || a.isNumber || a.isBoolean){
			isString = false;
		}

		return isString;
	}

	public static boolean IsNoType(Data a){
		boolean isNoType;

		if(!a.isString && !a.isStruture && !a.isArray && !a.isNumber && !a.isBoolean){
			isNoType = true;
		}else{
			isNoType = false;
		}

		return isNoType;
	}

  public static void delete(Object object){
    // Java has garbage collection.
  }
}

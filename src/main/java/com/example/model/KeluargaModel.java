package com.example.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeluargaModel {
	private String id;
	private String nomor_kk;
	private String alamat;
	private String rt;
	private String rw;
	private String id_kelurahan;
	private int is_tidak_berlaku;
	
	private KelurahanModel kelurahan;
	private List<PendudukModel> penduduks;
	
	public String getNama_kelurahan(){
		return kelurahan.getNama_kelurahan();
	}
}

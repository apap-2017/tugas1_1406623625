package com.example.service;

import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mapper.*;
import com.example.model.*;

import lombok.extern.slf4j.Slf4j;


@Service
public class PendudukServiceDatabase implements PendudukService
{
    @Autowired
    private PendudukMapper pendudukMapper;

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(PendudukServiceDatabase.class);
    
    @Override
    public PendudukModel selectPenduduk (String nik)
    {
        log.info ("select penduduk with nik {}", nik);
        return pendudukMapper.selectPenduduk (nik);
    }
    
  	@Override
  	public KeluargaModel selectKeluarga (String nkk) {
  		KeluargaModel keluarga = pendudukMapper.selectKeluargaByNKK(nkk);
  		List<PendudukModel> penduduk = pendudukMapper.selectPendudukList(keluarga.getId());
  		KelurahanModel kelurahan = pendudukMapper.selectKelurahan(keluarga.getId_kelurahan());
  		KecamatanModel kecamatan = pendudukMapper.selectKecamatan(kelurahan.getId_kecamatan());
  		KotaModel kota = pendudukMapper.selectKota(kecamatan.getId_kota());
  		keluarga.setPenduduks(penduduk);
  		keluarga.setKelurahan(kelurahan);
  		kelurahan.setKecamatan(kecamatan);
  		kecamatan.setKota(kota);
  		return keluarga;
  	}
	
  	
  	@Override
  	public String cekStatus(String nik){
  		PendudukModel penduduk = pendudukMapper.selectPenduduk(nik);
  		System.out.println(penduduk.getId());
  		int statusBaru = 2;
  		if(penduduk.getIs_wafat() == 0){
  			statusBaru = 1 ;
  		}
  		else{
  			statusBaru = 0;
  		}
  		penduduk.setIs_wafat(statusBaru);
  		pendudukMapper.ubahStatus(penduduk);
  		if(statusBaru == 1 ){
  			KeluargaModel keluarga = pendudukMapper.selectKeluarga(penduduk.getId_keluarga());
  			List<PendudukModel> penduduklist = pendudukMapper.selectPendudukList(keluarga.getNomor_kk());
  			if(penduduklist == null){
  					keluarga.setIs_tidak_berlaku(1);
  			}
  		}
  		return penduduk.getNik();
  	}

}
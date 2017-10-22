package com.example.service;

import java.util.List;

import com.example.model.*;


public interface PendudukService
{
    PendudukModel selectPenduduk (String nik);
    KeluargaModel selectKeluarga (String nkk);
	String cekStatus(String nik);

}
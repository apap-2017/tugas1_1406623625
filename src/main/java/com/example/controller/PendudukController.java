package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.*;
import com.example.service.*;
import com.example.mapper.*;


@Controller
public class PendudukController {
	@Autowired
    PendudukService pendudukDAO;
	KeluargaService keluargaDAO;
	
	@RequestMapping("/")
	public String home(){
		return "index";
	}
	
	@RequestMapping("/penduduk")
    public String cariNik (Model model, @RequestParam(value = "nik", required = false) String nik)
    {
        PendudukModel penduduk = pendudukDAO.selectPenduduk (nik);

        if (penduduk != null) {
            model.addAttribute ("penduduk", penduduk);
            return "viewPenduduk";
        } else {
            model.addAttribute ("nik", nik);
            return "not-found";
        }
    }
	
	@RequestMapping("/keluarga")
    public String cariNkk(Model model, @RequestParam(value= "nkk", required = false) String nkk)
    {		
		KeluargaModel keluarga = pendudukDAO.selectKeluarga (nkk);
		
		if(keluarga != null){
			model.addAttribute("keluarga",keluarga);
					return "viewKeluarga";
		} 	
		else {
				return "not-found";
		}
			
    }
	
	@RequestMapping("/penduduk/mati")
    public String ubahStatus(@RequestParam(value="nik", required=false) String nik, Model model)
    {
		String niktemp = pendudukDAO.cekStatus(nik);
		model.addAttribute("nik", niktemp);
		return "ubah-status";
    }
			

}

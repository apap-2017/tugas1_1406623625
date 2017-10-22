package com.example.mapper;

import java.util.List;

import com.example.model.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Many;

@Mapper
public interface PendudukMapper {
	@Select("select * from penduduk where nik = #{nik}")
	@Results(value = { @Result(property = "id", column = "id"), @Result(property = "nik", column = "nik"),
			@Result(property = "nama", column = "nama"), @Result(property = "tempat_lahir", column = "tempat_lahir"),
			@Result(property = "tanggal_lahir", column = "tanggal_lahir"),
			@Result(property = "jenis_kelamin", column = "jenis_kelamin"),
			@Result(property = "is_wni", column = "is_wni"), @Result(property = "id_keluarga", column = "id_keluarga"),
			@Result(property = "agama", column = "agama"), @Result(property = "pekerjaan", column = "pekerjaan"),
			@Result(property = "status_perkawinan", column = "status_perkawinan"),
			@Result(property = "status_dalam_keluarga", column = "status_dalam_keluarga"),
			@Result(property = "golongan_darah", column = "golongan_darah"),
			@Result(property = "is_wafat", column = "is_wafat") })
	PendudukModel selectPenduduk(@Param("nik") String nik);

	@Select("select id,id_kelurahan, alamat, rt, rw " + "from keluarga where id = #{id_keluarga}")
	@Results(value = { @Result(property = "alamat", column = "alamat"), @Result(property = "rt", column = "rt"),
			@Result(property = "rw", column = "rw") })
	KeluargaModel selectKeluarga(@Param("id_keluarga") String id_keluarga);

	@Select("select id, nomor_kk, id_kelurahan, alamat, rt, rw " + "from keluarga where nomor_kk = #{nkk}")
	@Results(value = { @Result(property = "alamat", column = "alamat"), @Result(property = "id", column = "id"),
			@Result(property = "nomor_kk", column = "nomor_kk"), @Result(property = "rt", column = "rt"),
			@Result(property = "rw", column = "rw") })
	KeluargaModel selectKeluargaByNKK(@Param("nkk") String nkk);

	@Select("select id_kecamatan, nama_kelurahan " + "from kelurahan where id = #{id_kelurahan}")
	@Results(value = { @Result(property = "nama_kelurahan", column = "nama_kelurahan") })
	KelurahanModel selectKelurahan(@Param("id_kelurahan") String id_kelurahan);

	@Select("select * " + "from kecamatan where id = #{id_kecamatan}")
	@Results(value = { @Result(property = "nama_kecamatan", column = "nama_kecamatan"),
			@Result(property = "id", column = "id"), })
	KecamatanModel selectKecamatan(@Param("id_kecamatan") String id_kecamatan);

	@Select("select id,nama_kota " + "from kota where id = #{id_kota}")
	@Results(value = { @Result(property = "nama_kota", column = "nama_kota") })
	KotaModel selectKota(@Param("id_kota") String id_kota);

	@Select("select nik, nama, tempat_lahir, id_keluarga, tanggal_lahir, jenis_kelamin, golongan_darah, agama, is_wni, status_perkawinan, pekerjaan, is_wafat "
			+ "from penduduk where id_keluarga = #{id}")
	@Results(value = { @Result(property = "nik", column = "nik"), @Result(property = "nama", column = "nama"),
			@Result(property = "tempat_lahir", column = "tempat_lahir"),
			@Result(property = "tanggal_lahir", column = "tanggal_lahir"),
			@Result(property = "jenis_kelamin", column = "jenis_kelamin"),
			@Result(property = "golongan_darah", column = "golongan_darah"),
			@Result(property = "agama", column = "agama"), @Result(property = "is_wni", column = "is_wni"),
			@Result(property = "status_perkawinan", column = "status_perkawinan"),
			@Result(property = "perkerjaan", column = "perkerjaan"),
			@Result(property = "is_wafat", column = "is_wafat"),
			@Result(property = "id_keluarga", column = "id_keluarga") })
	List<PendudukModel> selectPendudukList(@Param("id") String id);
	
	@Select("SELECT * from penduduk where id_keluarga IN (select id from keluarga where id_kelurahan = #{id})")
	@Results(value = { @Result(property = "nomor_kk", column = "nomor_kk") })
	List<PendudukModel> selectKeluargaList(@Param("id") String id);

	@Select("select id,nama_kecamatan from kecamatan where id_kota = #{id}")
	List<KecamatanModel> selectKecamatanList(@Param("id") String id);

	@Select("select id, nama_kelurahan from kelurahan where id_kecamatan = #{id}")
	List<KelurahanModel> selectKelurahanList(@Param("id") String id);

	@Update("update penduduk SET is_wafat = #{is_wafat} where id = #{id}")
	void ubahStatus(PendudukModel penduduk);

}

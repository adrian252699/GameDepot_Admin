package com.example.GameDepot_Admin.service.mappers;

import com.example.GameDepot_Admin.dto.videojuego.*;
import com.example.GameDepot_Admin.model.videojuego.*;

import java.util.ArrayList;
import java.util.List;

public class VideojuegoMapper {

    public static List<MostrarVideojuegoDTO> toMostrarVideojuegoDTOList(List<Videojuego> videojuegos){
        List<MostrarVideojuegoDTO> mostrarVideojuegoDTO = new ArrayList<>();
        for (Videojuego videojuego : videojuegos) {
            mostrarVideojuegoDTO.add(toMostrarVideojuegoDTO(videojuego));
        }

        return mostrarVideojuegoDTO;
    }

    public static Videojuego toVideojuegoEntity(GuardarVideojuegoDTO videojuegoDTO){
        Videojuego videojuego = new Videojuego(
                videojuegoDTO.getNombre(),
                toCategoriaEntity(videojuegoDTO.getCategoria()),
                videojuegoDTO.getDescripcion(),
                videojuegoDTO.getPrecio(),
                videojuegoDTO.getStock(),
                videojuegoDTO.getActivo(),
                toPlataformaEntity(videojuegoDTO.getPlataforma()),
                toRegionEntity(videojuegoDTO.getRegion())

        );

        videojuego.setNombre(videojuegoDTO.getNombre());
        videojuego.setDescripcion(videojuegoDTO.getDescripcion());
        videojuego.setPrecio(videojuegoDTO.getPrecio());
        videojuego.setCategoria(toCategoriaEntity(videojuegoDTO.getCategoria()));
        videojuego.setActivo(videojuegoDTO.getActivo());
        videojuego.setStock(videojuegoDTO.getStock());


        return videojuego;
    }

    public static Region toRegionEntity(RegionDTO regionDTO){
        Region region = new Region();
        region.setNombre(regionDTO.getNombre());
        region.setId(regionDTO.getId());
        return region;
    }
    public static Plataforma toPlataformaEntity(PlataformaDTO plataformaDTO){
        Plataforma plataforma = new Plataforma();
        plataforma.setNombre(plataformaDTO.getNombre());
        plataforma.setId(plataformaDTO.getId());
        return plataforma;
    }

    public static GuardarVideojuegoDTO toGuardarVideojuegoDTO(Videojuego videojuego) {
        if (videojuego == null) {
            return null;
        }

        Categoria categoria = videojuego.getCategoria();

        CategoriaDTO categoriaDTO = new CategoriaDTO(videojuego.getCategoria().getId(),categoria.getNombre(),categoria.getDescripcion());

        return new GuardarVideojuegoDTO(
                videojuego.getNombre(),
                categoriaDTO,
                videojuego.getDescripcion(),
                videojuego.getPrecio(),
                videojuego.getStock(),
                videojuego.getActivo(),
                toPlataformaDTO(videojuego.getPlataforma()),
                toRegionDTO(videojuego.getRegion())
        );
    }

    public static RegionDTO toRegionDTO(Region region) {
        RegionDTO regionDTO = new RegionDTO();
        regionDTO.setId(region.getId());
        regionDTO.setNombre(region.getNombre());

        return regionDTO;
    }

    public static List<RegionDTO> toRegionDTOList(List<Region> regions) {
        List<RegionDTO> regionDTOList = new ArrayList<>();
        for (Region region : regions) {
            regionDTOList.add(toRegionDTO(region));

        }
        return regionDTOList;
    }

    public static PlataformaDTO toPlataformaDTO(Plataforma plataforma) {
        PlataformaDTO plataformaDTO = new PlataformaDTO();
        plataformaDTO.setNombre(plataforma.getNombre());
        plataformaDTO.setId(plataforma.getId());

        return plataformaDTO;
    }

    public static List<PlataformaDTO>  toPlataformaDTOList(List<Plataforma> plataformas) {
        List<PlataformaDTO> plataformaDTOList = new ArrayList<>();
        for (Plataforma plataforma : plataformas) {
            plataformaDTOList.add(toPlataformaDTO(plataforma));
        }
        return plataformaDTOList;
    }

    public static List<ClaveDTO> toClaveDTOList(List<Clave> claves){
        List<ClaveDTO> claveDTOList = new ArrayList<>();
        for (Clave clave : claves) {
            claveDTOList.add(toClaveDTO(clave));
        }

        return claveDTOList;
    }

    public static ClaveDTO toClaveDTO(Clave clave){
        ClaveDTO claveDTO = new ClaveDTO();
        claveDTO.setId(clave.getId());
        claveDTO.setCodigo(clave.getCodigo());
        claveDTO.setItemId(clave.getItemOrden().getId());

        return claveDTO;
    }

    public static MostrarVideojuegoDTO toMostrarVideojuegoDTO(Videojuego videojuego) {
        if (videojuego == null) {
            return null;
        }

        Categoria categoria = videojuego.getCategoria();

        CategoriaDTO categoriaDTO = new CategoriaDTO(videojuego.getCategoria().getId(),categoria.getNombre(),categoria.getDescripcion());

        return new MostrarVideojuegoDTO(
                videojuego.getId(),
                videojuego.getNombre(),
                categoriaDTO,
                videojuego.getDescripcion(),
                videojuego.getPrecio(),
                videojuego.getStock(),
                videojuego.getActivo(),
                toPlataformaDTO(videojuego.getPlataforma()),
                toRegionDTO(videojuego.getRegion())

        );
    }

    public static CategoriaDTO toCategoriaDTO(Categoria categoria) {
        if (categoria == null) {
            return null;
        }

        return new CategoriaDTO(categoria.getId(), categoria.getNombre(),categoria.getDescripcion());
    }

    public static List<CategoriaDTO> toCategoriaDTOList(List<Categoria> categorias) {
        List<CategoriaDTO> categoriaDTOList = new ArrayList<>();
        for (Categoria categoria : categorias) {
            categoriaDTOList.add(toCategoriaDTO(categoria));
        }
        return categoriaDTOList;
    }

    public static Categoria toCategoriaEntity(CategoriaDTO categoria) {
        if (categoria == null) {
            return null;
        }

        return new Categoria(categoria.getId(), categoria.getNombre(),categoria.getDescripcion());
    }

    public static ActualizarVideojuegoDTO toActualizarVideojuegoDTO(Videojuego videojuego) {
        if (videojuego == null) {
            return null;
        }

        Categoria categoria = videojuego.getCategoria();

        CategoriaDTO categoriaDTO = new CategoriaDTO(videojuego.getCategoria().getId(),categoria.getNombre(),categoria.getDescripcion());

        return new ActualizarVideojuegoDTO(
                videojuego.getId(),
                videojuego.getNombre(),
                categoriaDTO,
                videojuego.getDescripcion(),
                videojuego.getPrecio(),
                videojuego.getStock(),
                videojuego.getActivo()
        );
    }
}

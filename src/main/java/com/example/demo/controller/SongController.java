package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.Playlist;
import com.example.demo.entities.Song;
import com.example.demo.services.SongService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class SongController 
{
	@Autowired
	SongService service;
	@PostMapping("/addSong")
	public String addSong(@ModelAttribute Song song)
	{
		boolean songStutus=service.songExists(song.getName());
		if(songStutus==false)
		{
			service.addSong(song);
			System.out.println("song added sucesssfully");
		}
		else
		{
			System.out.println("song already exists");
		}
		return "adminHome";
	}
	@GetMapping("/viewSongs")
	public String viewSongs(Model model)
	{
		List<Song> songList=service.fetchAllSongs();
		//  System.out.println(songList);
		model.addAttribute("songs",songList);

		return "displaySongs";
	}

	@GetMapping("/playSongs")
	public String playSongs(Model model)
	{
		boolean premiumUser=true;
		if(premiumUser==true)
		{
			List<Song> songList=service.fetchAllSongs();
			//  System.out.println(songList);
			model.addAttribute("songs",songList);
			return "displaySongs";
		}
		else
		{
			return "makePayment";
		}
	}
	
	
}

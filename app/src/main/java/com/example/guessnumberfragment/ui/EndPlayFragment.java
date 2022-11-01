package com.example.guessnumberfragment.ui;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guessnumberfragment.databinding.FragmentEndPlayBinding;
import com.example.guessnumberfragment.R;

public class EndPlayFragment extends Fragment {

    FragmentEndPlayBinding binding;

    public EndPlayFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        binding = FragmentEndPlayBinding.inflate(inflater);
        //binding.setMessage(new Message());
        binding.btnRepetir.setOnClickListener(view -> NavHostFragment.findNavController(this).navigate(R.id.action_endPlayFragment_to_configFragment));

        //binding.tvIntentosFinal.setText(jugador.getNombre() + " has " + jugador.getPartida() + " en " + jugador.getnIntentosActual() + " intentos.");
        //binding.tvnumAdivinar.setText("El número secreto era " + jugador.getNumAdivinar());

        return binding.getRoot();
    }

}
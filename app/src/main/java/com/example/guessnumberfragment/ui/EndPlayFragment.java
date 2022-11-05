package com.example.guessnumberfragment.ui;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEndPlayBinding.inflate(inflater);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setJugador(EndPlayFragmentArgs.fromBundle(getArguments()).getJugador());

        binding.tvIntentosFinal.setText(binding.getJugador().getNombre() + " has " + binding.getJugador().getPartida() + " en " + binding.getJugador().getnIntentosActual() + " intentos.");
        binding.tvnumAdivinar.setText("El número secreto era " + binding.getJugador().getNumAdivinar());
    }

    @Override
    public void onStart() {
        super.onStart();
        binding.btnRepetir.setOnClickListener(view -> NavHostFragment.findNavController(this).navigate(R.id.action_endPlayFragment_to_configFragment));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
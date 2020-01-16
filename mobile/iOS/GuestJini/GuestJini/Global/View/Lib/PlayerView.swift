//
//  PlayerView.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 16/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct PlayerView: UIViewRepresentable {
    var videoURL:URL?
    func updateUIView(_ uiView: UIView, context: UIViewRepresentableContext<PlayerView>) {
    }
    
    func makeUIView(context: Context) -> UIView {
        let playerView = PlayerUIView(frame: .zero)
        if(videoURL != nil){
            playerView.play(url: videoURL!)
        }
        return playerView
    }
}

//
//  PlayerUIView.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 16/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI
import AVFoundation

class PlayerUIView: UIView {
    private let playerLayer = AVPlayerLayer()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    override func layoutSubviews() {
        super.layoutSubviews()
        playerLayer.frame = bounds
    }
    
    func play(url: URL) -> Void {
        let player = AVPlayer(url: url)
        player.play()
        playerLayer.player = player
        layer.addSublayer(playerLayer)
    }
}

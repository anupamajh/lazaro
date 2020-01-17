//
//  AudioPlayerView.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 17/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct AudioPlayerView: View {
    var audioURL: URL?
    
    @ObservedObject var audioPlayer = AudioPlayer()
    
    var body: some View {
        HStack {
            if(self.audioURL != nil){
            Text("\(audioURL!.lastPathComponent)")
        }
            Spacer()
            if audioPlayer.isPlaying == false {
                Button(action: {
                    self.audioPlayer.startPlayback(audio: self.audioURL!)
                }) {
                    Image(systemName: "play.circle")
                        .imageScale(.large)
                }
            } else {
                Button(action: {
                    self.audioPlayer.stopPlayback()
                   
                }) {
                    Image(systemName: "stop.fill")
                        .imageScale(.large)
                }
            }
        }
    }
}



struct AudioPlayerView_Previews: PreviewProvider {
    static var previews: some View {
        AudioPlayerView()
    }
}

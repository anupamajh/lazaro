//
//  VideoPicker.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 16/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI
import Combine
import AVFoundation
import AVKit
import MobileCoreServices

struct VideoPickerView: UIViewControllerRepresentable {
    
    func updateUIViewController(_ uiViewController: UIImagePickerController, context: UIViewControllerRepresentableContext<VideoPickerView>) {
        
    }
    
    @Binding var model: VideoPickerViewModel
    @Binding var isCamera: Bool
    
    typealias UIViewControllerType = UIImagePickerController
    
    func makeCoordinator() -> VideoCoordinator {
        VideoCoordinator(self)
    }
    
    func makeUIViewController(context: UIViewControllerRepresentableContext<Self>) -> UIImagePickerController {
        
        let controller = UIImagePickerController()
        controller.delegate = context.coordinator
        controller.allowsEditing = false
        controller.mediaTypes = [kUTTypeMovie as String]
        if(isCamera){
            controller.sourceType = .camera
        } else{
            controller.sourceType = .photoLibrary
        }
        return controller
        
    }
    
    func updateUIViewController(_ uiViewController: UIImagePickerController, context: UIViewControllerRepresentableContext<ImagePickerView>) {
        // run right after making
        
    }
    
    class VideoCoordinator : NSObject, UIImagePickerControllerDelegate, UINavigationControllerDelegate {
        
        var parentView: VideoPickerView
        
        init(_ parentView: VideoPickerView) {
            self.parentView = parentView
        }
        
        func imagePickerControllerDidCancel(_ picker: UIImagePickerController) {
            parentView.model.isPresented = false
        }
        
        func imagePickerController(_ picker: UIImagePickerController,
                                   didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey: Any]) {
            
            guard let selectedVideo:URL = info[.mediaURL] as? URL else {return }
            let videoData = try? Data(contentsOf: selectedVideo)
            let playerView = PlayerView(videoURL: selectedVideo)
            parentView.model.sourceVideo = videoData
            parentView.model.pickedVideoSubject?.send(playerView)
            parentView.model.isPresented = false
            
        }
        
    }
    
}



struct VideoPickerViewModel {
    var isPresented: Bool = false
    var sourceVideo:Data? = nil
    let pickedVideoSubject: PassthroughSubject<PlayerView, Never>! = PassthroughSubject<PlayerView, Never>()
}

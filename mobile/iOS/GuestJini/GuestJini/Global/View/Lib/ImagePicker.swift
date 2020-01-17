//
//  ImagePicker.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 14/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI
import Combine

struct ImagePickerView : UIViewControllerRepresentable {
    
    @Binding var model: ImagePickerViewModel
    @Binding var isCamera: Bool
    
    typealias UIViewControllerType = UIImagePickerController
    
    func makeCoordinator() -> Coordinator {
        Coordinator(self)
    }
    
    func makeUIViewController(context: UIViewControllerRepresentableContext<Self>) -> UIImagePickerController {
        
        let controller = UIImagePickerController()
        controller.delegate = context.coordinator
        controller.allowsEditing = false
        controller.mediaTypes = ["public.image"]
        if(isCamera){
            controller.sourceType = .camera
        } else{
            controller.sourceType = .savedPhotosAlbum
        }
        return controller
        
    }
    
    func updateUIViewController(_ uiViewController: UIImagePickerController, context: UIViewControllerRepresentableContext<ImagePickerView>) {
        // run right after making
        
    }
    
    class Coordinator : NSObject, UIImagePickerControllerDelegate, UINavigationControllerDelegate {
        
        var parentView: ImagePickerView
        
        init(_ parentView: ImagePickerView) {
            self.parentView = parentView
        }
        
        func imagePickerControllerDidCancel(_ picker: UIImagePickerController) {
            parentView.model.isPresented = false
        }
        
        func imagePickerController(_ picker: UIImagePickerController,
                                   didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey: Any]) {
            
            guard let uiImage = info[.originalImage] as? UIImage else {
                parentView.model.isPresented = false
                return
            }
            
            let image = Image(uiImage: uiImage)
            parentView.model.sourceImage = uiImage
            parentView.model.sourceImageData = uiImage.jpegData(compressionQuality: 0.7)
            parentView.model.pickedImagesSubject?.send(image)
            parentView.model.isPresented = false
        }
        
        
    }
    
}

struct ImagePickerViewModel {
    var isPresented: Bool = false
    var sourceImage:UIImage? = nil
    var sourceImageData:Data? = nil
    var sourceFileName:String? = nil
    
    let pickedImagesSubject: PassthroughSubject<Image, Never>! = PassthroughSubject<Image, Never>()
}

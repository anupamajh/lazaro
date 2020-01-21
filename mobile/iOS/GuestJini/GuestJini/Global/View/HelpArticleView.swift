//
//  HelpArticleView.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 21/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct HelpArticleView: View {
    @ObservedObject var viewRouter: ViewRouter
    
    @State var authorPic:Image = Image(systemName: "person")
    @State var reviewText:String = ""
    var body: some View {
        GeometryReader { geometry in
            VStack{
                 VStack{
                    ScrollView{
                        
                        HStack{
                            Button(action:{
                                self.viewRouter.currentPage = ViewRoutes.FIND_HELP_PAGE
                            }){
                                GuestJiniButtonSystemImagePlain(imageName: "arrow.left")
                                Spacer()
                            }
                        }.padding()
                        VStack{
                            HStack{
                                VStack{
                                    ZStack{
                                        self.authorPic
                                            .resizable()
                                            .clipShape(Circle())
                                            .overlay(Circle().stroke(Color.white, lineWidth: 1))
                                    }
                                }.frame(width: 45, height: 45, alignment: .center)
                                VStack{
                                    HStack{
                                        Text("Author - John Doe")
                                            .font(Fonts.RobotSectionTitle)
                                            .foregroundColor(Color("greyishBrownFour"))
                                            .bold()
                                        Spacer()
                                    }
                                    HStack{
                                        Text("10 Jan 2019")
                                            .font(Fonts.RobotRegularSmallText)
                                            .foregroundColor(Color("brownishGrey"))
                                            .bold()
                                        Spacer()
                                    }
                                }
                                Spacer()
                            }.padding()
                            
                            VStack{
                                HStack{
                                    Text("Elevator is not working?")
                                        .font(Fonts.RobotTitle)
                                        .foregroundColor(Color("greyishBrownFour"))
                                        .bold()
                                    Spacer()
                                }.padding(.bottom)
                                HStack{
                                    VStack{
                                        Text("Lorem ipsum dolor sit amet, consectetur  adipiscing elit. Etiam erat sapien, ultricies quis  sagittis ut, posuere eu eros. Suspendisse non  feugiat neque. Sed ac magna nec turpis tincidunt  ullamcorper nec in risus. Ut risus purus, suscipit  et accumsan sit amet, ultrices quis leo. In non se mper diam. Cras dignissim porta velit, quis ullamcorper mifaucibus in. Duis rutrum orci sed.   ")
                                            .font(Fonts.RobotRegular)
                                            .foregroundColor(Color("greyishBrownFour"))
                                            .multilineTextAlignment(.leading)
                                            .lineLimit(500)
                                        
                                        Spacer()
                                    }
                                }.frame(minHeight:200, maxHeight: .infinity)
                            }.padding()
                            
                            VStack{
                                VStack{
                                    ZStack{
                                        VStack{
                                            HStack{
                                                Text("Was this Helpful?")
                                                    .foregroundColor(Color("warmGrey"))
                                                    .font(Fonts.RobotSectionTitle)
                                                Spacer()
                                                VStack{
                                                    Button(action: {
                                                        
                                                    }){
                                                        GuestJiniRoundButtonSystemImage(systemImage: "hand.thumbsup")
                                                    }
                                                    Text("90%").font(Fonts.RobotRegularSmallText)
                                                }
                                                VStack{
                                                    Button(action: {
                                                        
                                                    }){
                                                        GuestJiniRoundButtonSystemImage(systemImage: "hand.thumbsdown")
                                                    }
                                                    Text("10%").font(Fonts.RobotRegularSmallText)
                                                }
                                                
                                            }
                                            
                                        }.padding()
                                            .background(Color("paleGrey"))
                                        
                                    }
                                    
                                }.padding(.top)
                                    .padding(.trailing)
                                    .padding(.bottom)
                                    .padding(.leading, 40)
                                    .cornerRadius(5)
                            }
                            
                            VStack{
                                VStack{
                                    ZStack{
                                        VStack{
                                            HStack{
                                                Text("Write a Review")
                                                    .foregroundColor(Color("warmGrey"))
                                                    .font(Fonts.RobotSectionTitle)
                                                Spacer()
                                            }
                                            VStack{
                                                MultilineTextView(text: self.$reviewText)
                                                    .frame(height:100, alignment: .top)
                                                    .padding(.all,10)
                                                    .font(Fonts.RobotRegular)
                                                    .background(Color.white)
                                                    .overlay(
                                                        RoundedRectangle(cornerRadius:5)
                                                            .stroke(Color("veryLightPink"), lineWidth: 1)
                                                )
                                            }
                                            HStack{
                                                Button(action: {
                                                    
                                                }){
                                                    GuestJiniButtonText(buttonText: "SUBMIT")
                                                }
                                                Spacer()
                                            }
                                        }.padding()
                                            .background(Color("paleGrey"))
                                        
                                    }
                                    
                                }.padding(.top)
                                    .padding(.trailing)
                                    .padding(.bottom)
                                    .padding(.leading, 40)
                                    .cornerRadius(5)
                            }
                            
                        }
                    }
                }.frame(width: geometry.size.width, height: geometry.size.height-85, alignment: .top)
                    .padding()
                Divider()
                GuestJiniBottomBar(viewRouter: self.viewRouter)
                    .background(Color.white)
            }.frame(width: geometry.size.width, height: geometry.size.height, alignment: .top)
                .edgesIgnoringSafeArea(.vertical)
        }
    }
}

struct HelpArticleView_Previews: PreviewProvider {
    static var previews: some View {
        HelpArticleView(viewRouter: ViewRouter())
    }
}

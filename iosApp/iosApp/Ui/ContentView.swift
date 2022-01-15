import SwiftUI
import data
import domain
import utils

struct ContentView: View {
    
	var body: some View {
        TabView {
            
            HomeView()
                .tabItem {
                    Image(systemName: "house.fill")
                    Text("Home")
                }
            
            MapView()
                .tabItem {
                    Image(systemName: "map.fill")
                    Text("Map")
                }
            
            MyDateView()
                .tabItem {
                    Image(systemName: "heart.fill")
                    Text("My Date")
                }
            
            MenuView()
            
                .tabItem {
                    Image(systemName: "person.fill")
                    Text("Profile")
                }
            
        }.accentColor(Color.primary)

	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
	ContentView()
	}
}

import { AfterViewInit, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { LeafletModule } from '@asymmetrik/ngx-leaflet';
import * as L from 'leaflet';
import { Location } from '../../classes/location';
import { LocationService } from '../../services/location.service';
import { Marker } from '../../classes/marker';
import { MarkerService } from '../../services/marker.service';
import { APIData } from '../../classes/apidata';
import { APIDataService } from '../../services/apidata.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-map',
  standalone: true,
  imports: [LeafletModule],
  templateUrl: './map.component.html',
  styleUrl: './map.component.scss'
})
export class MapComponent implements AfterViewInit{
  private yx: any = L.latLng;
  private xy(x:any, y:any) {
    if (Array.isArray(x)) { // When doing xy([x, y]);
        return this.yx(x[1], x[0]);
    }
    return this.yx(y, x); // When doing xy(x, y);
}
  private subscription!: Subscription;
  private map: any;
  private bounds: any = [[0,0], [6144, 8192]];
  private APIData!: APIData;
  private locations!: Location[];
  private location!: Location;
  private lat!: number;
  private long!: number;
  private marker!:any;
  private markers!:Marker[];
  private locationName!: string;
  private newMarker!: Marker;

  // MARKER DOM
  private markerPopup!: any;
  private markerButton!: any;
  private locationButton!: any;
  private modifyButton!: any;
  private markerName!: any;
  private markerDescription!: any;

  constructor(public APIDataService:APIDataService, 
              private locationService:LocationService, 
              private markerService:MarkerService, 
              private route: ActivatedRoute,
              private router: Router){}

  ngOnInit(){
    this.route.params.subscribe(params =>{
      this.locationName = params['location'];
      console.log(this.locationName)
    })

    this.initMap(this.locationName);
    this.APIDataService.findByMapName(this.locationName).subscribe(data => {
      this.APIData = data;
      this.location = this.APIData.data;
      this.markers = this.location.markers;
      console.log(this.markers);
      this.markers.forEach(marker => {
        this.lat = marker.latitude;
        this.long = marker.longitude;
        this.marker = L.latLng([this.lat,this.long]);
        console.log(marker)

        this.markerPopup = document.createElement('div');

        this.markerName = document.createElement('b');
        this.markerName.innerHTML = marker.referencedLocation.name;
        this.markerPopup.appendChild(this.markerName);
        this.markerPopup.appendChild(document.createElement('br'));

        this.markerDescription = document.createElement('p');
        this.markerDescription.innerHTML = marker.referencedLocation.description;
        this.markerPopup.appendChild(this.markerDescription);

        this.markerButton = document.createElement("button");
        this.markerButton.addEventListener("click", this.deleteMarker, false);
        this.markerButton.markerId = marker.id;
        this.markerButton.innerHTML = "Delete Marker";
        this.markerPopup.appendChild(this.markerButton);

        this.modifyButton = document.createElement("button");
        this.modifyButton.addEventListener("click", this.modifyLocation, false);
        this.modifyButton.locationId = marker.referencedLocation.id;
        this.modifyButton.innerHTML = "Modify Location";
        this.markerPopup.appendChild(this.modifyButton);

        if (marker.referencedLocation.hasOwnProperty("mapName")) {
          this.locationButton = document.createElement("button");
          this.locationButton.addEventListener("click", this.goToMap, false);
          this.locationButton.mapName = marker.referencedLocation.mapName;
          this.locationButton.innerHTML = "Go To Map";
          this.markerPopup.appendChild(this.locationButton);
        } 
        var icon = L.icon({iconUrl: '../../assets/marker-icon.png',
          shadowUrl: '../../assets/marker-shadow.png'});
        L.marker(this.marker,{icon: icon}).addTo(this.map).bindPopup(this.markerPopup);
        
      });
    })
    this.map.on('contextmenu', this.addMarker)
  }

  private initMap(locationName:string): void {
    this.map = L.map('map', {
      crs: L.CRS.Simple,
      zoom: 3,
      minZoom: -2,
      maxZoom: 8,
      maxBounds: [[-100,-100], [6244, 8292]],
      maxBoundsViscosity: 0.0
    });

    const map = L.imageOverlay('../../assets/' + locationName+ '.jpg', this.bounds).addTo(this.map);
    this.map.setView(this.xy(4000, 3000), -2);
  }

  ngAfterViewInit(): void {
    
    this.map.on("click", (e:any) => {
      console.log(e.latlng); // get the coordinates
    });
  }

  public addMarker = (event:any) => {
    var markerLatLng = L.marker(event.latlng).addTo(this.map);
    console.log(markerLatLng);
    var newLocation:Location = this.addLocation();
    const newMarker = new Marker(event.latlng.lat, event.latlng.lng, newLocation, this.location);
    console.log(newMarker);
    this.markerService.save(newMarker).subscribe(data =>
      console.log("Creado marcador con id: " + data.id)
    )
    location.reload();
  }

  private addLocation():Location{
    var name = window.prompt("Inserta nombre de la localización", "");
    var description = window.prompt("Inserta descripción de la localización", "");
    var link = window.prompt("Inserta enlace de la wiki de la localización", "");
    var newLocation:Location = new Location(name, description, link);
    return newLocation;
  }

  private modifyLocation = (event:any) =>{
    var updatedLocation = this.addLocation();
    console.log("Llega aquí");
    this.locationService.update(updatedLocation, event.currentTarget.locationId).subscribe(data =>
      console.log("Modificada la localización")
    );
    location.reload();
  }
  
  public deleteMarker = (event:any) => {
    var markerId:number = event.currentTarget.markerId;
    this.markerService.delete(markerId).subscribe(data => 
      console.log("Borrado realizado del marcador con id: " + data.id) 
    );
    location.reload();
  }

  public goToMap = (event:any) => {
    console.log(event.currentTarget.mapName)
    this.router.navigate(['map/'+ event.currentTarget.mapName]).then(() =>{window.location.reload()})
    
  }

  
}

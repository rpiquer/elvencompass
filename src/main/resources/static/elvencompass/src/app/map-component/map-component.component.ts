import { Component } from '@angular/core';
import { LeafletModule } from '@asymmetrik/ngx-leaflet';
import L from 'leaflet';

@Component({
  selector: 'app-map-component',
  standalone: true,
  imports: [LeafletModule],
  templateUrl: './map-component.component.html',
  styleUrl: './map-component.component.scss'
})
export class MapComponentComponent {

  ngOnInit() {
    const myMap = L.map('map').setView([51.505, -0.09], 13);
    L.tileLayer('../../assets/faerun.jpg', {
            attribution: '© <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(myMap);
}
}

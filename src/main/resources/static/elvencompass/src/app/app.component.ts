import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MapComponentComponent } from './map-component/map-component.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, MapComponentComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'elvencompass';
}

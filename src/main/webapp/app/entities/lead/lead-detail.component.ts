import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILead } from 'app/shared/model/lead.model';

@Component({
  selector: 'jhi-lead-detail',
  templateUrl: './lead-detail.component.html'
})
export class LeadDetailComponent implements OnInit {
  lead: ILead;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ lead }) => {
      this.lead = lead;
    });
  }

  previousState() {
    window.history.back();
  }
}

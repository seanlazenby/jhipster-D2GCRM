/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { D2GcrmTestModule } from '../../../test.module';
import { LeadDeleteDialogComponent } from 'app/entities/lead/lead-delete-dialog.component';
import { LeadService } from 'app/entities/lead/lead.service';

describe('Component Tests', () => {
  describe('Lead Management Delete Component', () => {
    let comp: LeadDeleteDialogComponent;
    let fixture: ComponentFixture<LeadDeleteDialogComponent>;
    let service: LeadService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [D2GcrmTestModule],
        declarations: [LeadDeleteDialogComponent]
      })
        .overrideTemplate(LeadDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LeadDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LeadService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete('123');
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith('123');
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
